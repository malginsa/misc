package lcpr.sc;

public class ElementContext {

    private Element currentElement;
    private boolean isCurrentElementToDelete;

    public ElementContext() {
    }

    public Element getCurrentElement() {
        return currentElement;
    }

    public boolean isCurrentElementToDelete() {
        return isCurrentElementToDelete;
    }

    public void putNextElement(String tagName, String text) {
        currentElement.setTagName(tagName);
        currentElement.setText(text);
    }
}
