// Java program To calculate  
// The Value Of nCr and nPr
class Arithmetic {

    // Permutations
    static int nCr(int n, int r)
    {
        return fact(n) / (fact(r) *
                fact(n - r));
    }

    // Permutations
    static int nPr(int n, int r)
    {
        return fact(n) / fact(n - r);
    }

    public static int [] pSieve(int max)
    {
        boolean [] primes = new boolean [max-1];
        int count = 0;

        for (int j = 0; j < max-1; j++)
            primes[j] = true;

        int i = 2;
        while(i < max)
        {
            if (!primes[i-2])
            {
                i++;
                continue;
            }
            count++;
            int n = i+i;
            while (n <= max)
            {
                if (primes[n-2])
                    primes[n-2] = false;
                n += i;
            }
            i++;
        }
        int [] found = new int [count];
        int a = 0;
        for (int j = 0; j < max-1; j++)
        {
            if (primes[j])
                found[a++] = j+2;

        }
        return found;
    }
    // Euclids iterative
    public static int gcd2(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }

    public static void printArr(int [] a)
    {
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }

    // Returns factorial of n
    static int fact(int n)
    {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

}