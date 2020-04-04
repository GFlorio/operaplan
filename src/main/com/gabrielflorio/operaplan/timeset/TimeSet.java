package com.gabrielflorio.operaplan.timeset;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public final class TimeSet {
    private final ImmutableList<TimeInterval> intervals;

    private TimeSet(@NotNull Collection<TimeInterval> intervals) {
        Objects.requireNonNull(intervals);
        var normalized = new ArrayList<>(intervals);
        this.intervals = ImmutableList.copyOf(normalized);
    }

    public static TimeSet of(@NotNull Collection<TimeInterval> intervals) {
        Objects.requireNonNull(intervals);
        return new TimeSet(intervals);
    }

    public static TimeSet ofInterval(@NotNull Instant start, @NotNull Instant end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        return new TimeSet(ImmutableList.of(TimeInterval.between(start, end)));
    }

    public TimeSet union(TimeSet other) {
        Objects.requireNonNull(other);
        return null;
    }

    public TimeSet intersection(@NotNull TimeSet other) {
        Objects.requireNonNull(other);
        return null;
    }

    public TimeSet difference(@NotNull TimeSet other) {
        Objects.requireNonNull(other);
        return null;
    }

    public boolean contains(@NotNull Instant moment) {
        Objects.requireNonNull(moment);
        return false;
    }

    public boolean isSubset(@NotNull TimeSet of) {
        Objects.requireNonNull(of);
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public TimeInterval limitingInterval() {
        return null;
    }

    public Duration duration() {
        return null;
    }

    public ImmutableList<TimeInterval> intervals() { return intervals; }
}
