#include<stdio.h>

// print right triangles with character '0'
int main() {
    int n, i, j;
    scanf("%d", &n);
    
    for(i=0; i<n; i++) {
        for(j=0; j<=i; j++) {
            printf("0");
        }
        if(i != n-1) {
            printf("\n");
        }
    }

    return 0;
}
