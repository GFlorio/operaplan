package com.gabrielflorio.operaplan.timeset;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public final class TimeInterval {
    private final Instant start;
    private final Instant end;

    private TimeInterval(@NotNull Instant start, @NotNull Instant end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        if (!start.isBefore(end))
            throw new IllegalArgumentException("Start needs to be before end!");
        this.start = start;
        this.end = end;
    }

    public static TimeInterval between(@NotNull Instant startIncl, @NotNull Instant endExcl) {
        Objects.requireNonNull(startIncl);
        Objects.requireNonNull(endExcl);
        return new TimeInterval(startIncl, endExcl);
    }

    public boolean contains(@NotNull Instant moment) {
        Objects.requireNonNull(moment);
        return false;
    }

    public boolean overlapsWith(@NotNull TimeInterval other) {
        Objects.requireNonNull(other);
        return false;
    }

    public boolean isSubset(@NotNull TimeInterval of) {
        Objects.requireNonNull(of);
        return false;
    }

    public Duration duration() {
        return null;
    }

    public TimeInterval translatedBy(@NotNull Duration duration) {
        Objects.requireNonNull(duration);
        return null;
    }

    public Instant start() { return start; }
    public Instant end() { return end; }
}
