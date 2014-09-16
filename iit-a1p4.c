#include<stdio.h>

// Print whether a given matrix is upper or lower triangular.
int main() {
    long n, i, j, temp;
    long upperTriangleNonZeroNumbers, lowerTriangleNonZeroNumbers;
	
    scanf("%ld", &n);
    
    // the number of elements in either half 
    // is given by count of first n numbers formula
    // setting the counters to this initially
    upperTriangleNonZeroNumbers = lowerTriangleNonZeroNumbers = (n*(n-1))/2;

    // decrement counter if a zero is found in either half
    for(i=0; i<n; i++) {
        for(j=0; j<n; j++) {
            scanf("%ld", &temp);
            if(i > j && temp == 0) {
                upperTriangleNonZeroNumbers--;	
            } else if(i < j && temp == 0) {
                lowerTriangleNonZeroNumbers--;	
            } else if(i==j) { } // do nothing
        }
    }

    // if either counter becomes zero 
    // that means all numbers in that half were zero
    if(upperTriangleNonZeroNumbers==0 
        || lowerTriangleNonZeroNumbers ==  0) {
        printf("yes\n");
    } else {
        printf("no\n");
    }

   return 0;
}
