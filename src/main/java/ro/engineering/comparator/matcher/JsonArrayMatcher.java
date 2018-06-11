package ro.engineering.comparator.matcher;

import ro.engineering.comparator.JSONCompare;
import ro.engineering.util.StringUtil;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonArrayMatcher extends AbstractJsonMatcher {

    private Set<Integer> matchedPositions = new HashSet<Integer>();

    public JsonArrayMatcher(JsonNode expected, JsonNode actual) {
        super(expected, actual);
    }

    @Override
    public void matches() throws MatcherException {
        for (int i = 0; i < expected.size(); i++) {
            JsonNode element = expected.get(i);
            boolean found = false;
            for (int j = 0; j < actual.size(); j++) {
                if (matchedPositions.contains(j)) {
                    continue;
                }
                JsonNode actElement = actual.get(j);
                try {
                    new JsonMatcher(element, actElement).matches();
                } catch (MatcherException e) {
                    continue;
                }
                found = true;
                matchedPositions.add(j);
                break;
            }
            if (!found) {
                throw new MatcherException("Element NOT found:\n"
                        + StringUtil.cropSmall(JSONCompare.prettyPrint(element)));
            }
        }
    }
}
