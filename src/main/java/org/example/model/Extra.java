package org.example.model;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for extra information
 */
public class Extra {
    public int id;
    public String name;
    public int value;

    public Extra(int id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Extra() {
    }

    public static ExtraBuilder builder() {
        return new ExtraBuilder();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Extra)) return false;
        final Extra other = (Extra) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (this.getValue() != other.getValue()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Extra;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.getValue();
        return result;
    }

    public String toString() {
        return "Extra(id=" + this.getId() + ", name=" + this.getName() + ", value=" + this.getValue() + ")";
    }

    public static class ExtraBuilder {
        private int id;
        private String name;
        private int value;

        ExtraBuilder() {
        }

        public ExtraBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ExtraBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ExtraBuilder value(int value) {
            this.value = value;
            return this;
        }

        public Extra build() {
            return new Extra(this.id, this.name, this.value);
        }

        public String toString() {
            return "Extra.ExtraBuilder(id=" + this.id + ", name=" + this.name + ", value=" + this.value + ")";
        }
    }
}
