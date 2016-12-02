package com.parkingwang.lang.data;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.7
 */
public final class Either<L, R> {

    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L left() {
        return left;
    }

    public R right() {
        return right;
    }

    public boolean isLeft(){
        return left != null;
    }

    public boolean isRight(){
        return right != null;
    }

    @Override
    public String toString() {
        return "{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public static <L, R> Either<L, R> left(L left){
        return new Either<>(left, null);
    }

    public static <L, R> Either<L, R> right(R right){
        return new Either<>(null, right);
    }
}
