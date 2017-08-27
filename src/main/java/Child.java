import java.io.IOException;
import java.io.ObjectInputStream;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class Child extends Parent {
  private transient String bar;

  @RequiresNonNull("foo")
  @EnsuresNonNull("bar")
  private void initTransientFields() {
    bar = String.valueOf(foo);
  }

  public Child() {
    initTransientFields();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    assert foo != null : "@AssumeAssertion(nullness)"; // SHOULDN'T BE NECESSARY!
    initTransientFields();
  }
}
