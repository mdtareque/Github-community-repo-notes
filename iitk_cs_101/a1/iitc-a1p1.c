#include<stdio.h>

// Program to check whether given 3 integers form a pythagorean triplet or not
int main(){
    int a,b,c;
    long a2, b2, c2;

    // read input
    scanf("%d%d%d", &a, &b, &c);
    
    // store the squares of each number so as to not calculate them each time
    a2 = a*a;
    b2 = b*b;
    c2 = c*c;

    // check all combination that could form a pythagorean triplet
    if((a2) == (b2 + c2)) { printf("yes"); }
    else if((b2) == (a2 + c2)) { printf("yes"); }
    else if((c2) == (a2 + b2)) { printf("yes"); }
    else { printf("no"); }

 return 0;
}
