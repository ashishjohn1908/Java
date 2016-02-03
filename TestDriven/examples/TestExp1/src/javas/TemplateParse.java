package javas;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 27-Sep-2010
 * Time: 22:04:27
 * To change this template use File | Settings | File Templates.
 */
public class TemplateParse {

    public List<String> parse(String template) {
        List<String> segments = new ArrayList<String>();
        int index = collectSegments(segments, template);
        addTail(segments, template, index);
        addEmptyStringIfTemplateWasEmpty(segments);
        return segments;
    }

    private int collectSegments(List<String> segs, String src) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(src);
        int index = 0;
        while (matcher.find()) {
            addPrecedingPlainText(segs, src, matcher, index);
            addVariable(segs, src, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addTail(List<String> segs, String template, int index) {
        if (index < template.length()) {
            segs.add(template.substring(index));
        }
    }

    private void addVariable(List<String> segs, String src, Matcher m) {
        segs.add(src.substring(m.start(), m.end()));
    }

    private void addPrecedingPlainText(List<String> segs, String src, Matcher m, int index) {
        if (index != m.start()) {
            segs.add(src.substring(index, m.start()));
        }
    }

    private void addEmptyStringIfTemplateWasEmpty(List<String> segs) {
        if (segs.isEmpty()) {
            segs.add("");
        }
    }
}
