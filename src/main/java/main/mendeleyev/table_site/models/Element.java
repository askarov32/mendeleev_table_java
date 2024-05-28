package main.mendeleyev.table_site.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
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
