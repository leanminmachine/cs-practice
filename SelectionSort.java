class SelectionSort {
	public static void selectionSort(int[] a) {
		for (int i = a.length - 1; i >= 1; i--) {
			int index = i; // i is the last item position and
			               // index is hte largest element position
			for (int j = 0; j < i; j++) {
				if (a[j] > a[index]) 
					index = j;
			}
			int temp = a[index];
			a[index] = a[i];
			a[i] = temp;
		}
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 7, 12, 3, 5, -6, 3, 8, 2, 10, -3 };

		printArray(arr);
		selectionSort(arr);
		printArray(arr);
	}
}

