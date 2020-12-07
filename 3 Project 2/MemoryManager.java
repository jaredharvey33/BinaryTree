import java.io.IOException;
import java.io.RandomAccessFile;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.11.29
 * @param <K>
 *
 */
public class MemoryManager<K> {

    private DLinkedList<Pair> list;
    private RandomAccessFile raf;

    public MemoryManager(RandomAccessFile r) {
        list = new DLinkedList<>();
        raf = r;
    }


    public Handle<Pair> insert(K sequenceid, K sequence) throws IOException {
        String id = sequenceid.toString();
        String seq = sequence.toString();

        // convert id and seq to binary
        byte[] idBuffer = stringToBinary(id, id.length());
        int idLoc = writeBinary(idBuffer);
        byte[] seqBuffer = stringToBinary(seq, seq.length());
        int seqLoc = writeBinary(seqBuffer);

        Pair p1 = new Pair(idLoc, id.length());
        Pair p2 = new Pair(seqLoc, seq.length());
        Handle<Pair> h = new Handle<>(p1, p2);
        return h;
    }


    private int writeBinary(byte[] b) throws IOException {
        if (list.isEmpty()) {
            int idx = (int)raf.length();
            raf.seek(idx);
            raf.write(b);
            return idx;
        }
        int idx = bestFit(b.length);
        if (idx == -1) {
            idx = (int)raf.length();
        }
        raf.seek(idx);
        raf.write(b);
        return idx;
    }


    /**
     * @param length
     * @return
     */
    private int bestFit(int length) {
        int idx = -1;
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLen() >= length && Math.min(best, list.get(i)
                .getLen()) != best) {
                best = list.get(i).getLen();
                idx = i;
            }
        }
        return idx;
    }


    private byte[] stringToBinary(String id, int length) {
        // calculate number of bytes required
        int bytes = length / 4;
        if (length % 4 != 0) {
            bytes++;
        }

        byte[] buffer = new byte[bytes];

        int idx = 0;
        int bit = 7;
        for (int i = 0; i < length; i++) {

            // check for next byte
            if (i % 4 == 0 && i != 0) {
                idx++;
                bit = 7;
            }

            // set bits based on character
            if (id.charAt(i) == 'A') {
                buffer[idx] &= ~(1 << bit);
                bit--;
                buffer[idx] &= ~(1 << bit);
                bit--;
            }
            else if (id.charAt(i) == 'C') {
                buffer[idx] &= ~(1 << bit);
                bit--;
                buffer[idx] |= 1 << bit;
                bit--;
            }
            else if (id.charAt(i) == 'G') {
                buffer[idx] |= 1 << bit;
                bit--;
                buffer[idx] &= ~(1 << bit);
                bit--;
            }
            else if (id.charAt(i) == 'T') {
                buffer[idx] |= 1 << bit;
                bit--;
                buffer[idx] |= 1 << bit;
                bit--;
            }
        }

        return buffer;

    }


    public String getKey(Handle<Pair> handle) throws IOException {
        int offset = handle.getSeqID().getLoc();
        int length = (handle.getSeqID().getLen() + 3) / 4;
        byte[] b = new byte[length];
        raf.seek(offset);
        raf.read(b);
        String id = binaryToString(b, handle.getSeqID().getLen());
        return id;
    }


    /**
     * @param b
     * @param len
     * @return
     */
    private String binaryToString(byte[] b, int len) {
        String s = "";
        int idx = -1;
        int bit = 7;

        for (int i = 0; i < len; i++) {
            if (i % 4 == 0) {
                idx++;
                bit = 7;
            }

            // check two bits at a time to determine character to append

            if ((b[idx] & (1 << (bit))) == 0) {
                bit--;
                if ((b[idx] & (1 << (bit))) == 0) {
                    bit--;
                    s = s.concat("A");
                }
                else {
                    bit--;
                    s = s.concat("C");
                }
            }
            else {
                bit--;
                if ((b[idx] & (1 << (bit))) == 0) {
                    bit--;
                    s = s.concat("G");
                }
                else {
                    bit--;
                    s = s.concat("T");
                }
            }
        }
        return s;
    }


    public void remove(Handle<Pair> h) {
        int idLoc = h.getSeqID().getLoc();
        int idLen = h.getSeqID().getLen();
        idLen = (idLen + 3) / 4;
        int seqLoc = h.getSeq().getLoc();
        int seqLen = h.getSeq().getLen();
        seqLen = (seqLen + 3) / 4;
        Pair freeID = new Pair(idLoc, idLen);
        Pair freeSeq = new Pair(seqLoc, seqLen);

        addFreeBlock(freeID);
        list.add(freeID);
        list.add(freeSeq);
    }


    private void addFreeBlock(Pair free) {
        int idx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (free.getLoc() < list.get(i).getLoc()) {
                idx = i;
                break;
            }
        }
        
        // check if blocks need to be merged
        if (idx - 1 >= 0) {
            if (list.get(idx - 1).getLoc() + list.get(idx - 1).getLen() == free
                .getLoc()) {
                list.get(idx - 1).setLen(list.get(idx - 1).getLen() + free
                    .getLen());
            }
        }
        else if (idx + 1 < list.size()) {
            if (free.getLen() + free.getLoc() == list.get(idx).getLoc()) {
                
            }
        }
        else {
            list.add(idx, free);
        }

    }


    public void printList() {
        System.out.println(list.toString());
    }
}
