#include <bits/stdc++.h>

using namespace std;
typedef long double ld;

static ld M;
static ld F;
static ld maxDoors;

/**
 * Change this to reflect the function you wish to ternary search
 * 
 * Doing divisions after multiplications improves accuracy
 */
static ld f(ld d){
    return (1 / (maxDoors - d)) * (M - pow((d * F), 2));
}

/**
 * Change comparator to find min point
 * Only guaranteed to work if there is only
 * one turning point in interval
 */
static ld* searchMax(ld start, ld end, ld* limits){
    ld length = end - start;
    ld splitOne = start + length / 3;
    ld splitTwo = start + 2 * length / 3;

    if(length == 0){
        limits[0] = start;
        limits[1] = end;

        return limits;
    }
    else if(f(splitOne) > f(splitTwo)){
        end = splitTwo;
    }
    else{
        start = splitOne;
    }


    limits[0] = start;
    limits[1] = end;
    return limits;
}

/**
 * Accurate to 10^-6
 * Fiddle with set precision to get better/worse acccuracy
 */
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> maxDoors;
    cin >> M;
    cin >> F;

    ld start = 0;
    ld end = maxDoors - 1;

    ld* limits = (ld*) malloc(2 * sizeof(ld));

    for(int i = 0; i < 100; i++){
        limits = searchMax(start, end, limits);
        start = limits[0];
        end = limits[1];
    }

    cout << setprecision(8) << f(start);

    return 0;
}
