package cloud.crosstraining.devstore.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class FindAllArgs {
    private Map<String,Object> filters;
    private String[] includes;
    private int page;
    private int size;
    private String[] sort;
    private Boolean desc;

    public FindAllArgs() {
        this.page =0;
        this.size = 20;
        this.sort = new String[] {"id"};
        this.desc = false;
        this.includes =  new String[]{};
        this.filters = new HashMap<>();

    }
    public FindAllArgs(String filters, String includes,Integer page, Integer size, String sort, Boolean desc) {
        this.page = page!=null?page:0;
        this.size = size!=null?size:20;
        this.sort = sort!=null? sort.split(","):null;
        this.desc = desc!=null?desc:false;
        this.includes = includes!=null? includes.split(","):null;
        this.filters = new HashMap<String,Object>();
        if (filters!=null) {
            String[] _filters = filters.split(",");
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
}
