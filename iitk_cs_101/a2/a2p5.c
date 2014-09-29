#include<stdio.h>

int gcd(int a, int b){
    return (b==0)?a:gcd(b,a%b);
}

int main(){
   int n, countOfCoPrimes = 1, i;
   scanf("%d", &n);
   if(n==1) { printf("0"); return 0;}
   for(i=2; i<=n; i++) {
       if((gcd(i,n)) == 1) countOfCoPrimes++;
   }
   printf("%d", countOfCoPrimes);
   return 0;
}
