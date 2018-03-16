package lcpr.sc;

public class PrintElementObserver implements IElementObserver {

    @Override
    public void handleEvent(ElementContext elementContext) {

        Element element = elementContext.getCurrentElement();
        String tagName = element.getTagName();
        String text = element.getText();

        StringBuilder builder = new StringBuilder();
        builder.append("<" + tagName + ">");
        builder.append(text);
        builder.append("</" + tagName + ">");
        builder.append("/n");

        System.out.println(builder.toString());
    }
}
