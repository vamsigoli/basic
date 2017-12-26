import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Integer.valueOf;

//used to practice the column selection mode in intellij. see the video in heiz intellij tutorial
public class DataHolder implements Serializable {
    private int i;
    private float f;
    private boolean b;
    private long l;

    public DataHolder(int i, float f, boolean b, long l) {
        this.i = i;
        this.f = f;
        this.b = b;
        this.l = l;
    }

    private void readObject(ObjectInputStream inputStream) throws IOException {
        i = inputStream.readInt ();
        f = inputStream.readFloat ();
        b = inputStream.readBoolean ();
        l = inputStream.readLong ();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeInt (i);
        outputStream.writeFloat (f);
        outputStream.writeBoolean (b);
        outputStream.writeLong (l);

    }
}

