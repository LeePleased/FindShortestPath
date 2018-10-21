package utils;

import graph.Site;
import java.util.List;

public class helper {

    /**
     * 按标识名在一个列表中查询.
     */
    public static Site queryInList(String name, List<Site> siteList) {
        for(Site site : siteList) {
            if (site.getName().equals(name)) {
                return site;
            }
        }

        // 如果查询不存在, 则返回 null.
        return null;
    }
}
