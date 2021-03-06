package application;

import java.sql.Timestamp;

public class NullableTimestamp extends Timestamp {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullableTimestamp() {
        super(0L);
    }
    public NullableTimestamp(long value) {
        super(value);
    }

    @Override
    public String toString() {
        return this.getTime() > 0L ? super.toString() : "";
    }

    /*public static NullableTimestamp valueOf(LocalDateTime localDateTime) {
        return new NullableTimestamp(Timestamp.valueOf(localDateTime).getTime());
    }*/
}