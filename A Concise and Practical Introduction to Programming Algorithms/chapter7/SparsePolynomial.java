class SparsePolynomial {
    int degree;
    double coefficient;
    SparsePolynomial next;

    SparsePolynomial(int d, double v, SparsePolynomial poly) {
        this.degree = d;
        this.coefficient = v;
        this.next = poly;
    }
}