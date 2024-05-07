package org.example.model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for keeping the track of id, type, time, calorie, date, extra
 */
public class Training implements Comparator<Training> {
    public int id;
    public int time;
    public int calorie;
    public LocalDate date;
    public int userId;
    public int typeId;
    public int extraId;

    public Training(int id, int time, int calorie, LocalDate date, int userId, int typeId, int extraId) {
        this.id = id;
        this.time = time;
        this.calorie = calorie;
        this.date = date;
        this.userId = userId;
        this.typeId = typeId;
        this.extraId = extraId;
    }

    public Training() {
    }

    public static TrainingBuilder builder() {
        return new TrainingBuilder();
    }


    @Override
    public int compare(Training o1, Training o2) {
        return o1.getDate().compareTo(o2.getDate());
    }

    public int getId() {
        return this.id;
    }

    public int getTime() {
        return this.time;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public int getExtraId() {
        return this.extraId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setExtraId(int extraId) {
        this.extraId = extraId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Training)) return false;
        final Training other = (Training) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (this.getTime() != other.getTime()) return false;
        if (this.getCalorie() != other.getCalorie()) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        if (this.getUserId() != other.getUserId()) return false;
        if (this.getTypeId() != other.getTypeId()) return false;
        if (this.getExtraId() != other.getExtraId()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Training;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getTime();
        result = result * PRIME + this.getCalorie();
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        result = result * PRIME + this.getUserId();
        result = result * PRIME + this.getTypeId();
        result = result * PRIME + this.getExtraId();
        return result;
    }

    public String toString() {
        return "Training(id=" + this.getId() + ", time=" + this.getTime() + ", calorie=" + this.getCalorie() + ", date=" + this.getDate() + ", userId=" + this.getUserId() + ", typeId=" + this.getTypeId() + ", extraId=" + this.getExtraId() + ")";
    }

    public static class TrainingBuilder {
        private int id;
        private int time;
        private int calorie;
        private LocalDate date;
        private int userId;
        private int typeId;
        private int extraId;

        TrainingBuilder() {
        }

        public TrainingBuilder id(int id) {
            this.id = id;
            return this;
        }

        public TrainingBuilder time(int time) {
            this.time = time;
            return this;
        }

        public TrainingBuilder calorie(int calorie) {
            this.calorie = calorie;
            return this;
        }

        public TrainingBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public TrainingBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public TrainingBuilder typeId(int typeId) {
            this.typeId = typeId;
            return this;
        }

        public TrainingBuilder extraId(int extraId) {
            this.extraId = extraId;
            return this;
        }

        public Training build() {
            return new Training(this.id, this.time, this.calorie, this.date, this.userId, this.typeId, this.extraId);
        }

        public String toString() {
            return "Training.TrainingBuilder(id=" + this.id + ", time=" + this.time + ", calorie=" + this.calorie + ", date=" + this.date + ", userId=" + this.userId + ", typeId=" + this.typeId + ", extraId=" + this.extraId + ")";
        }
    }
}
