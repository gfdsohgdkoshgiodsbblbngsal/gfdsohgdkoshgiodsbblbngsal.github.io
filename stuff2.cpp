#include <iostream>
#include <map>
#include <algorithm>

using namespace std;

int main() {
    freopen("mowing.in", "r", stdin);
    freopen("mowing.out", "w", stdout);

    int N, S, time = 0, x = -1;
    char D;
    pair<int, int> pos = {0, 0};
    map<pair<int, int>, int> lastCut;

    cin >> N;

    for (int i = 0; i < N; ++i) {
        cin >> D >> S;
        for (int step = 0; step < S; ++step) {
            if (D == 'N') pos.second++;
            else if (D == 'S') pos.second--;
            else if (D == 'W') pos.first--;
            else if (D == 'E') pos.first++;

            time++;

            if (lastCut.count(pos)) {
                int last = lastCut[pos];
                if (x == -1) x = time - last;
                else x = min(x, time - last);
            }

            lastCut[pos] = time;
        }
    }

    cout << x << endl;

    return 0;
}
