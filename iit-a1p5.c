#include<stdio.h>

int main() {
    long n, max = -1, max2 = -1;
    
    // atleast two numbers in input expected exclusing -1
    scanf("%ld", &n);
    max = n;
    
    while(n != -1) {
        scanf("%ld", &n);
        if(n == -1) break;
        if(n > max) {
        	max2 = max;
        	max = n;
        } else if( max2 == -1) {
        	max2 = n;
        }
    };
    
    printf("%ld", max2);
    
    return 0;
}
