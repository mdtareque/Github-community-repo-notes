#include<stdio.h>

int main() {
   long n, sum, expectedSum, i, temp;
   scanf("%d", &n);
   
   expectedSum = (n*(n+1)) / 2;
   
   sum=0;
   for(i=1; i<n; i++){
       scanf("%d", &temp);
       sum += temp;
   }
   printf("%ld", expectedSum - sum);
   
   return 0;
}
