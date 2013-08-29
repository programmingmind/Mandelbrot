import java.math.BigDecimal;

public class Worker implements Runnable {
	private BigDecimal precision, yVal, startX, endX;
	private final long maxIt, prevMaxIt;
	private final int count;
	private long[] iters;

	private static final BigDecimal FOUR = new BigDecimal(4.00);
	private static final BigDecimal TWO = new BigDecimal(2.00);

	public Worker(BigDecimal startX, BigDecimal endX, BigDecimal y, BigDecimal precision, long maxIt, long prevMaxIt, long[] prevIts) {
		this.startX = startX;
		this.endX = endX;
		this.yVal = y;
		this.precision = precision;
		this.maxIt = maxIt;
		this.prevMaxIt = prevMaxIt;

		this.count = endX.subtract(startX).divideToIntegralValue(precision).intValue();
		if (prevIts != null && prevIts.length == count) {
			iters = prevIts;
		}
		else {
			iters = new long[count];
			if (prevIts.length == count / 2) {
				for (int i = 0; i < count / 2; i++)
					iters[i * 2] = prevIts[i];
			}
		}
	}

	public Worker(BigDecimal startX, BigDecimal endX, BigDecimal y, BigDecimal precision, long maxIt) {
		this(startX, endX, y, precision, maxIt, 0, null);
	}

	@Override
	public void run() {
		for (int ndx = 0; startX.compareTo(endX) < 0; startX = startX.add(precision), ndx++) {
			if (iters[ndx] < prevMaxIt)
				continue;

			long it = 0;
			BigDecimal x = startX, y = yVal;
			while (it < maxIt && x.multiply(x).add(y.multiply(y)).compareTo(FOUR) < 0) {
				BigDecimal Xtemp = x.multiply(x).subtract(y.multiply(y)).add(startX);
				y = TWO.multiply(x).multiply(y).add(yVal);
				x = Xtemp;
				it++;
			}
			iters[ndx] = it;
		}
	}
}