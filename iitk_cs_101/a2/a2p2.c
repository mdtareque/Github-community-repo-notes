include<stdio.h>

float fact(int f) {
    int res=1;
    while(f>0) {
       res*=f;
       f--;
    }
    return res;
}


float ncr(int a,int b) {
   return fact(a)/(fact(b)*fact(a-b));
}

int main() {
    int n ,k ,ans;
    scanf("%d%d",&n,&k);
    ans = ncr(n,k);
    printf("%ld", ans);
    return 0;
}
