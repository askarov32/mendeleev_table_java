package main.mendeleyev.table_site.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Element {
    private int id;
    private int element_id;
    private String name;
    private String group;
    private String period;
    private String icon;
    private String formula;
    private String valency;
}
