#include<stdio.h>

// Function to calculate x raised to the power y
long power(int x, unsigned int y) {
    long temp;
    if( y == 0)
        return 1;
    temp = power(x, y/2);
    if (y%2 == 0) {
        return temp*temp;
    } else {
        return x*temp*temp;
    }
}

// Program to calcuate sum of fourth powers of first N numbers
int main() {
   int n;
   double result;
   // take input
   scanf("%d", &n);

   // calculate
   result = (1.0/5.0)*power(n,5) + (1.0/2.0)*power(n,4) + (1.0/3.0)*power(n,3) - (1.0/30.0)*n;

   // display
   printf("%.0f", result);
   
   return 0;
}
