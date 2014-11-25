// prev permutation
#include <stdio.h>

/*
 *----------------------------------------------------
 * For detailed explanation, please see:
 * https://drive.google.com/file/d/0BwzHeT8eBmvCM3pEc2hpVGJLQTA/view?usp=sharing
 *-----------------------------------------------------
 */

/*
 *--------------------------------------------------------
 * Program to find the permutation which lexicographically
 * precedes a given permutation.
 *
 * Algorithm:
 *  1. Find the longest ascending suffix, say p[j]...p[n-1]
 *  2. Prev = p[j-1]
 *  3. Swap Prev and the maximum among the elements in 
 *                    p[j]...p[n-1] which are lesser than Prev
 *  4. Reverse p[j]...p[n-1]
 *---------------------------------------------------------
 */


/*-----------------
 * Find the longest ascending suffix in a[] of size n.
 * Returns the starting position of the longest ascending suffix.
 *---------------- 
 */
int longest_ascending_suffix(int a[], int n)
{
	int i;
	int prev=a[n-1];
	for( i=n-2; i>=0; i-- ) {
		if(a[i]>prev){
			return i+1;
		}
		prev = a[i];
	}
	return 0;
}

int swap(int a[], int i, int j)
{
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

/*--------------------
 * a[] has n elements
 * return the position of the first occurrence of k in a[].
 *
 * return -1 if k is absent in the array
 *--------------------
 */
int find(int a[], int n, int k)
{
	int i = 0;
	for ( i=0; i<n; i++ ){
		if (a[i] == k) {
			return i;
		}
	}
	return -1;
}

/*---------------------
 * reverse the subarray a[start]...a[end]
 *---------------------
 */
void reverse(int a[], int start, int end)
{
	int mid = (start+end)/2;
	int temp;
	int i;

	for (i=0; start+i <= mid; i++){
		swap(a, start+i, end-i);
	}
	return;
}
void previous_permutation(int a[], int n)
{
	int i;
	int start; /* start of the longest ascending suffix */
	int prev;  /* element before the longest ascending suffix */

	start = longest_ascending_suffix(a,n);
	if ( start == 0 ) { /* no preceding permutation */
		return;
	}
	prev = a[start-1]; 
	/* find i such that a[i-1]<prev<a[i] in the ascending suffix */
	for(i=start; i<n; i++){
		if(a[i]>prev){
			break;
		}
	}
	/* a[i-1]<prev<a[i]. Swap prev and a[i-1] */
	swap ( a, start-1, i-1 );
	reverse ( a, start, n-1 );
	return;
}

int main()
{
	int n;
	int i;
	int a[40];

	scanf("%d",&n);

	for(i=0; i<n; i++){
		scanf("%d", &a[i]);
	}
	
	previous_permutation(a,n);

	for(i=0;i<n;i++){
		printf("%d ",a[i]);
	}
	printf("\n");
}
