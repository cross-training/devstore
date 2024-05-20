package cloud.crosstraining.devstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class FindArgs {
    private Map<String,Object> filters;
    private String[] includes;

    public FindArgs() {
        this.includes =  new String[]{};
        this.filters = new HashMap<>();

    }
    public FindArgs(String filters, String includes) {
        this.includes = includes!=null? includes.split(","):null;
        String[] _filters = filters!=null?filters.split(","):null;
        this.filters = new HashMap<String,Object>();
        for(String _filter: _filters ) {
            String[] entries = _filter.split("=");
            if (entries.length == 2) {
                this.filters.put(entries[0],entries[1]);
            } else if (entries.length ==1) {
                this.filters.put(entries[0],true);
            }
        }
    }
}
