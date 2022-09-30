import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MultiThreadingMergeSort extends Thread {
	public static <E> void sort(E[] a, Comparator<? super E> comp, int threads, FileWriter wr) {
		parallelMergeSort(a, 0, a.length - 1, comp, threads, wr);
	}

	private static <E> void mergeSort(E[] a, int from, int to, Comparator<? super E> comp, FileWriter wr) {
		if (from == to) {
			return;
		}
		if (to - from > 0) {
			int mid = (from + to) / 2;

			mergeSort(a, from, mid, comp, wr);
			mergeSort(a, mid + 1, to, comp, wr);
			merge(a, from, mid, to, comp, wr);
		}
	}

	private static <E> void parallelMergeSort(E[] a, int from, int to, Comparator<? super E> comp, int availableThreads, FileWriter wr) {
		if (to - from > 0) {
			if (availableThreads <= 1) {
				mergeSort(a, from, to, comp, wr);
			} else {
				int middle = to / 2;

				Thread firstHalf = new Thread() {
					public void run() {
						try {
							wr.write("\n" + getName() + " started: ");
						} catch (IOException e) {
							e.printStackTrace();
						}
						parallelMergeSort(a, from, middle, comp, availableThreads - 1, wr);
					}
				};
				Thread secondHalf = new Thread() {
					public void run() {
						try {
							wr.write("\n" + getName() + " started: ");
						} catch (IOException e) {
							e.printStackTrace();
						}
						parallelMergeSort(a, middle + 1, to, comp, availableThreads - 1, wr);
					}
				};

				firstHalf.start();
				secondHalf.start();

				try {
					firstHalf.join();
					if (!firstHalf.isAlive()) {
						wr.write('\n' + firstHalf.getName() + " finished:");
					}

					secondHalf.join();
					if (!secondHalf.isAlive()) {
						wr.write('\n' + secondHalf.getName() + " finished:");
					}

				} catch (InterruptedException ie) {
					ie.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				merge(a, from, middle, to, comp, wr);

			}
		}
	}

	@SuppressWarnings("unchecked")
	private static <E> void merge(E[] a, int from, int mid, int to, Comparator<? super E> comp, FileWriter wr) {
		int n = to - from + 1;
		Object[] b = new Object[n];
		int i1 = from;
		int i2 = mid + 1;
		int j = 0;

		while (i1 <= mid && i2 <= to) {
			if (comp.compare(a[i1], a[i2]) < 0) {
				b[j] = a[i1];
				i1++;
			} else {
				b[j] = a[i2];
				i2++;
			}
			j++;
		}

		while (i1 <= mid) {
			b[j] = a[i1];
			i1++;
			j++;
		}

		while (i2 <= to) {
			b[j] = a[i2];
			i2++;
			j++;
		}

		for (j = 0; j < n; j++) {
			a[from + j] = (E) b[j];
		}
		
		for (int i = from; i <= to; i++) {
			try {
				wr.write(a[i] + ",");
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
}
