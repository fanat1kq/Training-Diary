package org.example.model;

public class Type {
    int id;
    String typeName;

    public Type(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Type() {
    }

    public static TypeBuilder builder() {
        return new TypeBuilder();
    }

    public int getId() {
        return this.id;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Type)) return false;
        final Type other = (Type) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$typeName = this.getTypeName();
        final Object other$typeName = other.getTypeName();
        if (this$typeName == null ? other$typeName != null : !this$typeName.equals(other$typeName)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Type;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $typeName = this.getTypeName();
        result = result * PRIME + ($typeName == null ? 43 : $typeName.hashCode());
        return result;
    }

    public String toString() {
        return "Type(id=" + this.getId() + ", typeName=" + this.getTypeName() + ")";
    }

    public static class TypeBuilder {
        private int id;
        private String typeName;

        TypeBuilder() {
        }

        public TypeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public TypeBuilder typeName(String typeName) {
            this.typeName = typeName;
            return this;
        }

        public Type build() {
            return new Type(this.id, this.typeName);
        }

        public String toString() {
            return "Type.TypeBuilder(id=" + this.id + ", typeName=" + this.typeName + ")";
        }
    }
}
