#include<stdio.h>

int gcd(int a, int b){
    return (b==0)?a:gcd(b,a%b);
}

int lcm(int a, int b){
    int max = (a>b)?a:b;
    int min = (a>b)?b:a;
    return (a*b)/gcd(max,min);
}

int main(){
    int n, a, b, i;
    scanf("%d", &n);
    scanf("%d", &a);
    for(i=1; i<n; i++) {
        scanf("%d", &b);
        a = lcm(a,b);
    }
    printf("%d", a);
    return 0;
}
