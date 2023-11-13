#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int area(int x1, int y1, int x2, int y2) {
    return (x2 - x1) * (y2 - y1);
}

int intersection(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
    int x_overlap = max(0, min(ax2, bx2) - max(ax1, bx1));
    int y_overlap = max(0, min(ay2, by2) - max(ay1, by1));
    return x_overlap * y_overlap;
}

int main() {
    ifstream fin("billboard.in");
    ofstream fout("billboard.out");
    
    int ax1, ay1, ax2, ay2, bx1, by1, bx2, by2, tx1, ty1, tx2, ty2;
    fin >> ax1 >> ay1 >> ax2 >> ay2;
    fin >> bx1 >> by1 >> bx2 >> by2;
    fin >> tx1 >> ty1 >> tx2 >> ty2;
    
    int total_area = area(ax1, ay1, ax2, ay2) + area(bx1, by1, bx2, by2);
    total_area -= intersection(ax1, ay1, ax2, ay2, tx1, ty1, tx2, ty2);
    total_area -= intersection(bx1, by1, bx2, by2, tx1, ty1, tx2, ty2);
    
    fout << total_area << endl;
    
    fin.close();
    fout.close();
    
    return 0;
}
