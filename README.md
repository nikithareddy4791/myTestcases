package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Item Model Tests")
class ItemTest {

    @Test
    @DisplayName("3-arg constructor sets all fields")
    void threeArgConstructor_setsAllFields() {
        Item item = new Item(10, "ECMS", 0);
        assertThat(item.getId()).isEqualTo(10);
        assertThat(item.getItemDesc()).isEqualTo("ECMS");
        assertThat(item.getInactiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("3-arg constructor with null values")
    void threeArgConstructor_nullValues() {
        Item item = new Item(null, null, null);
        assertThat(item.getId()).isNull();
        assertThat(item.getItemDesc()).isNull();
        assertThat(item.getInactiveFlg()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("id getter and setter")
        void id_getterAndSetter() {
            Item item = new Item(1, "ECMS", 0);
            item.setId(20);
            assertThat(item.getId()).isEqualTo(20);
        }

        @Test
        @DisplayName("itemDesc getter and setter")
        void itemDesc_getterAndSetter() {
            Item item = new Item(1, "ECMS", 0);
            item.setItemDesc("Court Records");
            assertThat(item.getItemDesc()).isEqualTo("Court Records");
        }

        @Test
        @DisplayName("inactiveFlg getter and setter")
        void inactiveFlg_getterAndSetter() {
            Item item = new Item(1, "ECMS", 0);
            item.setInactiveFlg(1);
            assertThat(item.getInactiveFlg()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent id returns same instance")
        void fluent_id() {
            Item item = new Item(1, "ECMS", 0);
            assertThat(item.id(99)).isSameAs(item);
            assertThat(item.getId()).isEqualTo(99);
        }

        @Test
        @DisplayName("fluent itemDesc returns same instance")
        void fluent_itemDesc() {
            Item item = new Item(1, "ECMS", 0);
            assertThat(item.itemDesc("Court Records")).isSameAs(item);
            assertThat(item.getItemDesc()).isEqualTo("Court Records");
        }

        @Test
        @DisplayName("fluent inactiveFlg returns same instance")
        void fluent_inactiveFlg() {
            Item item = new Item(1, "ECMS", 0);
            assertThat(item.inactiveFlg(1)).isSameAs(item);
            assertThat(item.getInactiveFlg()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("same fields are equal")
        void equals_sameFields() {
            assertThat(new Item(1, "ECMS", 0)).isEqualTo(new Item(1, "ECMS", 0));
        }

        @Test
        @DisplayName("different id not equal")
        void equals_differentId() {
            assertThat(new Item(1, "ECMS", 0)).isNotEqualTo(new Item(2, "ECMS", 0));
        }

        @Test
        @DisplayName("different desc not equal")
        void equals_differentDesc() {
            assertThat(new Item(1, "ECMS", 0)).isNotEqualTo(new Item(1, "Court Records", 0));
        }

        @Test
        @DisplayName("different inactiveFlg not equal")
        void equals_differentInactiveFlg() {
            assertThat(new Item(1, "ECMS", 0)).isNotEqualTo(new Item(1, "ECMS", 1));
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            Item a = new Item(1, "ECMS", 0);
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null() {
            assertThat(new Item(1, "ECMS", 0)).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType() {
            assertThat(new Item(1, "ECMS", 0)).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            assertThat(new Item(1, "ECMS", 0).hashCode())
                    .isEqualTo(new Item(1, "ECMS", 0).hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            assertThat(new Item(1, "ECMS", 0).hashCode())
                    .isNotEqualTo(new Item(2, "Court Records", 0).hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("contains class name")
        void toString_containsClassName() {
            assertThat(new Item(1, "ECMS", 0).toString()).contains("Item");
        }

        @Test
        @DisplayName("contains field values")
        void toString_containsValues() {
            Item item = new Item(10, "ECMS", 0);
            assertThat(item.toString()).contains("10").contains("ECMS");
        }

        @Test
        @DisplayName("null fields show null - covers toIndentedString(null)")
        void toString_nullFields() {
            assertThat(new Item(null, null, null).toString()).contains("null");
        }

        @Test
        @DisplayName("multiline value is indented - covers toIndentedString newline replacement")
        void toString_multilineValue() {
            Item item = new Item(1, "line1\nline2", 0);
            assertThat(item.toString()).contains("line1").contains("line2");
        }
    }
}

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Category Model Tests")
class CategoryTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        Category c = new Category();
        assertThat(c).isNotNull();
        assertThat(c.getId()).isNull();
        assertThat(c.getCategoryDesc()).isNull();
        assertThat(c.getInactiveFlg()).isNull();
    }

    @Test
    @DisplayName("3-arg constructor sets all fields")
    void threeArgConstructor_setsAllFields() {
        Category c = new Category(1, "Shootings", 0);
        assertThat(c.getId()).isEqualTo(1);
        assertThat(c.getCategoryDesc()).isEqualTo("Shootings");
        assertThat(c.getInactiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("3-arg constructor with null values")
    void threeArgConstructor_nullValues() {
        Category c = new Category(null, null, null);
        assertThat(c.getId()).isNull();
        assertThat(c.getCategoryDesc()).isNull();
        assertThat(c.getInactiveFlg()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("id getter and setter")
        void id_getterAndSetter() {
            Category c = new Category();
            c.setId(5);
            assertThat(c.getId()).isEqualTo(5);
        }

        @Test
        @DisplayName("categoryDesc getter and setter")
        void categoryDesc_getterAndSetter() {
            Category c = new Category();
            c.setCategoryDesc("Robberies");
            assertThat(c.getCategoryDesc()).isEqualTo("Robberies");
        }

        @Test
        @DisplayName("inactiveFlg getter and setter")
        void inactiveFlg_getterAndSetter() {
            Category c = new Category();
            c.setInactiveFlg(1);
            assertThat(c.getInactiveFlg()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent id returns same instance")
        void fluent_id() {
            Category c = new Category();
            assertThat(c.id(1)).isSameAs(c);
            assertThat(c.getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent categoryDesc returns same instance")
        void fluent_categoryDesc() {
            Category c = new Category();
            assertThat(c.categoryDesc("Shootings")).isSameAs(c);
            assertThat(c.getCategoryDesc()).isEqualTo("Shootings");
        }

        @Test
        @DisplayName("fluent inactiveFlg returns same instance")
        void fluent_inactiveFlg() {
            Category c = new Category();
            assertThat(c.inactiveFlg(0)).isSameAs(c);
            assertThat(c.getInactiveFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("fluent chaining works")
        void fluent_chaining() {
            Category c = new Category().id(1).categoryDesc("Shootings").inactiveFlg(0);
            assertThat(c.getId()).isEqualTo(1);
            assertThat(c.getCategoryDesc()).isEqualTo("Shootings");
            assertThat(c.getInactiveFlg()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("same fields are equal")
        void equals_sameFields() {
            assertThat(new Category(1, "Shootings", 0))
                    .isEqualTo(new Category(1, "Shootings", 0));
        }

        @Test
        @DisplayName("different id not equal")
        void equals_differentId() {
            assertThat(new Category(1, "Shootings", 0))
                    .isNotEqualTo(new Category(2, "Shootings", 0));
        }

        @Test
        @DisplayName("different desc not equal")
        void equals_differentDesc() {
            assertThat(new Category(1, "Shootings", 0))
                    .isNotEqualTo(new Category(1, "Robberies", 0));
        }

        @Test
        @DisplayName("different inactiveFlg not equal")
        void equals_differentInactiveFlg() {
            assertThat(new Category(1, "Shootings", 0))
                    .isNotEqualTo(new Category(1, "Shootings", 1));
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            Category a = new Category(1, "Shootings", 0);
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null() {
            assertThat(new Category(1, "Shootings", 0)).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType() {
            assertThat(new Category(1, "Shootings", 0)).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            assertThat(new Category(1, "Shootings", 0).hashCode())
                    .isEqualTo(new Category(1, "Shootings", 0).hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            assertThat(new Category(1, "Shootings", 0).hashCode())
                    .isNotEqualTo(new Category(2, "Robberies", 0).hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("contains class name")
        void toString_containsClassName() {
            assertThat(new Category(1, "Shootings", 0).toString()).contains("Category");
        }

        @Test
        @DisplayName("contains field values")
        void toString_containsValues() {
            assertThat(new Category(1, "Shootings", 0).toString())
                    .contains("1").contains("Shootings");
        }

        @Test
        @DisplayName("null fields show null - covers toIndentedString(null)")
        void toString_nullFields() {
            assertThat(new Category().toString()).contains("null");
        }

        @Test
        @DisplayName("multiline value is indented - covers toIndentedString newline replacement")
        void toString_multilineValue() {
            Category c = new Category(1, "line1\nline2", 0);
            assertThat(c.toString()).contains("line1").contains("line2");
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Status Model Tests")
class StatusTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        Status s = new Status();
        assertThat(s).isNotNull();
        assertThat(s.getId()).isNull();
        assertThat(s.getStatusDesc()).isNull();
        assertThat(s.getInactiveFlg()).isNull();
    }

    @Test
    @DisplayName("3-arg constructor sets all fields")
    void threeArgConstructor_setsAllFields() {
        Status s = new Status(1, "Not Started", 0);
        assertThat(s.getId()).isEqualTo(1);
        assertThat(s.getStatusDesc()).isEqualTo("Not Started");
        assertThat(s.getInactiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("3-arg constructor with null values")
    void threeArgConstructor_nullValues() {
        Status s = new Status(null, null, null);
        assertThat(s.getId()).isNull();
        assertThat(s.getStatusDesc()).isNull();
        assertThat(s.getInactiveFlg()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("id getter and setter")
        void id_getterAndSetter() {
            Status s = new Status();
            s.setId(4);
            assertThat(s.getId()).isEqualTo(4);
        }

        @Test
        @DisplayName("statusDesc getter and setter")
        void statusDesc_getterAndSetter() {
            Status s = new Status();
            s.setStatusDesc("Completed");
            assertThat(s.getStatusDesc()).isEqualTo("Completed");
        }

        @Test
        @DisplayName("inactiveFlg getter and setter")
        void inactiveFlg_getterAndSetter() {
            Status s = new Status();
            s.setInactiveFlg(1);
            assertThat(s.getInactiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("all status values can be set")
        void allStatusValues() {
            Status notStarted = new Status();
            notStarted.setId(1);
            notStarted.setStatusDesc("Not Started");
            Status inProgress = new Status();
            inProgress.setId(2);
            inProgress.setStatusDesc("In Progress");
            Status waiting = new Status();
            waiting.setId(3);
            waiting.setStatusDesc("Waiting");
            Status completed = new Status();
            completed.setId(4);
            completed.setStatusDesc("Completed");

            assertThat(notStarted.getId()).isEqualTo(1);
            assertThat(inProgress.getId()).isEqualTo(2);
            assertThat(waiting.getId()).isEqualTo(3);
            assertThat(completed.getId()).isEqualTo(4);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent id returns same instance")
        void fluent_id() {
            Status s = new Status();
            assertThat(s.id(1)).isSameAs(s);
            assertThat(s.getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent statusDesc returns same instance")
        void fluent_statusDesc() {
            Status s = new Status();
            assertThat(s.statusDesc("Completed")).isSameAs(s);
            assertThat(s.getStatusDesc()).isEqualTo("Completed");
        }

        @Test
        @DisplayName("fluent inactiveFlg returns same instance")
        void fluent_inactiveFlg() {
            Status s = new Status();
            assertThat(s.inactiveFlg(0)).isSameAs(s);
            assertThat(s.getInactiveFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("fluent chaining works")
        void fluent_chaining() {
            Status s = new Status().id(2).statusDesc("In Progress").inactiveFlg(0);
            assertThat(s.getId()).isEqualTo(2);
            assertThat(s.getStatusDesc()).isEqualTo("In Progress");
            assertThat(s.getInactiveFlg()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("same fields are equal")
        void equals_sameFields() {
            assertThat(new Status(1, "Not Started", 0))
                    .isEqualTo(new Status(1, "Not Started", 0));
        }

        @Test
        @DisplayName("different id not equal")
        void equals_differentId() {
            assertThat(new Status(1, "Not Started", 0))
                    .isNotEqualTo(new Status(2, "Not Started", 0));
        }

        @Test
        @DisplayName("different desc not equal")
        void equals_differentDesc() {
            assertThat(new Status(1, "Not Started", 0))
                    .isNotEqualTo(new Status(1, "Completed", 0));
        }

        @Test
        @DisplayName("different inactiveFlg not equal")
        void equals_differentInactiveFlg() {
            assertThat(new Status(1, "Not Started", 0))
                    .isNotEqualTo(new Status(1, "Not Started", 1));
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            Status a = new Status(1, "Not Started", 0);
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null() {
            assertThat(new Status(1, "Not Started", 0)).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType() {
            assertThat(new Status(1, "Not Started", 0)).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            assertThat(new Status(1, "Not Started", 0).hashCode())
                    .isEqualTo(new Status(1, "Not Started", 0).hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            assertThat(new Status(1, "Not Started", 0).hashCode())
                    .isNotEqualTo(new Status(4, "Completed", 0).hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("contains class name")
        void toString_containsClassName() {
            assertThat(new Status(1, "Not Started", 0).toString()).contains("Status");
        }

        @Test
        @DisplayName("contains field values")
        void toString_containsValues() {
            assertThat(new Status(4, "Completed", 0).toString())
                    .contains("4").contains("Completed");
        }

        @Test
        @DisplayName("null fields show null - covers toIndentedString(null)")
        void toString_nullFields() {
            assertThat(new Status().toString()).contains("null");
        }

        @Test
        @DisplayName("multiline value indented - covers toIndentedString newline replacement")
        void toString_multilineValue() {
            Status s = new Status(1, "line1\nline2", 0);
            assertThat(s.toString()).contains("line1").contains("line2");
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tag Model Tests")
class TagTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        Tag t = new Tag();
        assertThat(t).isNotNull();
        assertThat(t.getId()).isNull();
        assertThat(t.getTagDesc()).isNull();
        assertThat(t.getInactiveFlg()).isNull();
    }

    @Test
    @DisplayName("3-arg constructor sets all fields")
    void threeArgConstructor_setsAllFields() {
        Tag t = new Tag(1, "Gun", 0);
        assertThat(t.getId()).isEqualTo(1);
        assertThat(t.getTagDesc()).isEqualTo("Gun");
        assertThat(t.getInactiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("3-arg constructor with null values")
    void threeArgConstructor_nullValues() {
        Tag t = new Tag(null, null, null);
        assertThat(t.getId()).isNull();
        assertThat(t.getTagDesc()).isNull();
        assertThat(t.getInactiveFlg()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("id getter and setter")
        void id_getterAndSetter() {
            Tag t = new Tag();
            t.setId(5);
            assertThat(t.getId()).isEqualTo(5);
        }

        @Test
        @DisplayName("tagDesc getter and setter")
        void tagDesc_getterAndSetter() {
            Tag t = new Tag();
            t.setTagDesc("Gang");
            assertThat(t.getTagDesc()).isEqualTo("Gang");
        }

        @Test
        @DisplayName("inactiveFlg getter and setter - active")
        void inactiveFlg_active() {
            Tag t = new Tag();
            t.setInactiveFlg(0);
            assertThat(t.getInactiveFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("inactiveFlg getter and setter - inactive")
        void inactiveFlg_inactive() {
            Tag t = new Tag();
            t.setInactiveFlg(1);
            assertThat(t.getInactiveFlg()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent id returns same instance")
        void fluent_id() {
            Tag t = new Tag();
            assertThat(t.id(1)).isSameAs(t);
            assertThat(t.getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent tagDesc returns same instance")
        void fluent_tagDesc() {
            Tag t = new Tag();
            assertThat(t.tagDesc("Gun")).isSameAs(t);
            assertThat(t.getTagDesc()).isEqualTo("Gun");
        }

        @Test
        @DisplayName("fluent inactiveFlg returns same instance")
        void fluent_inactiveFlg() {
            Tag t = new Tag();
            assertThat(t.inactiveFlg(0)).isSameAs(t);
            assertThat(t.getInactiveFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("fluent chaining works")
        void fluent_chaining() {
            Tag t = new Tag().id(1).tagDesc("Gun").inactiveFlg(0);
            assertThat(t.getId()).isEqualTo(1);
            assertThat(t.getTagDesc()).isEqualTo("Gun");
            assertThat(t.getInactiveFlg()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("same fields are equal")
        void equals_sameFields() {
            assertThat(new Tag(1, "Gun", 0)).isEqualTo(new Tag(1, "Gun", 0));
        }

        @Test
        @DisplayName("different id not equal")
        void equals_differentId() {
            assertThat(new Tag(1, "Gun", 0)).isNotEqualTo(new Tag(2, "Gun", 0));
        }

        @Test
        @DisplayName("different desc not equal")
        void equals_differentDesc() {
            assertThat(new Tag(1, "Gun", 0)).isNotEqualTo(new Tag(1, "Gang", 0));
        }

        @Test
        @DisplayName("different inactiveFlg not equal")
        void equals_differentInactiveFlg() {
            assertThat(new Tag(1, "Gun", 0)).isNotEqualTo(new Tag(1, "Gun", 1));
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            Tag a = new Tag(1, "Gun", 0);
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null() {
            assertThat(new Tag(1, "Gun", 0)).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType() {
            assertThat(new Tag(1, "Gun", 0)).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            assertThat(new Tag(1, "Gun", 0).hashCode())
                    .isEqualTo(new Tag(1, "Gun", 0).hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            assertThat(new Tag(1, "Gun", 0).hashCode())
                    .isNotEqualTo(new Tag(2, "Gang", 0).hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("contains class name")
        void toString_containsClassName() {
            assertThat(new Tag(1, "Gun", 0).toString()).contains("Tag");
        }

        @Test
        @DisplayName("contains field values")
        void toString_containsValues() {
            assertThat(new Tag(1, "Gun", 0).toString()).contains("1").contains("Gun");
        }

        @Test
        @DisplayName("null fields show null - covers toIndentedString(null)")
        void toString_nullFields() {
            assertThat(new Tag().toString()).contains("null");
        }

        @Test
        @DisplayName("multiline value indented - covers toIndentedString newline replacement")
        void toString_multilineValue() {
            Tag t = new Tag(1, "line1\nline2", 0);
            assertThat(t.toString()).contains("line1").contains("line2");
        }
    }
}
