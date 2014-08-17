package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by tetn on 12/16/13.
 */
public class GameObject implements Serializable,
        Comparable<GameObject> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private BigInteger id = BigInteger.ZERO;

    @JsonProperty
    private String name = "";

    @JsonProperty
    private String description = "";

    protected GameObject() {
        super();
    }

    protected GameObject(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GameObject{" + "id=" + id + ", name='" + name + '\''
                + ", description='" + description + '\'' + '}';
    }

    @Override
    public int compareTo(GameObject another) {
        int result = this.getName().compareTo(another.getName());
        if (result == 0) {
            result = this.getDescription().compareTo(another.getDescription());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameObject)) {
            return false;
        }
        GameObject that = (GameObject) o;

        if (!description.equals(that.description)) {
            return false;
        }
        if (!id.equals(that.id)) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
