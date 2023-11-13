#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("paint.in");
    ofstream fout("paint.out");

    int a, b, c, d;
    fin >> a >> b >> c >> d;

    int left = min(a, c);
    int right = max(b, d);
    int overlap = max(0, min(b, d) - max(a, c));
    int totalLength = (b - a) + (d - c) - overlap;

    fout << totalLength << "\\n";

    fin.close();
    fout.close();

    return 0;
}
