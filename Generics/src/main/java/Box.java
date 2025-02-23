class Box<T extends Item> {
    private T contents;

    public Box() {}

    public void store(T item) {
        contents = item;
    }

    public T getContents() {
        return contents;
    }
}
