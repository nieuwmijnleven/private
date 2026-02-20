/*
 * JADEx - Java Advanced Development Extension
 *
 * Copyright (C) 2026 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 only,
 * as published by the Free Software Foundation.
 *
 * Alternatively, this software may be used under a commercial license
 * from Cheol Jeon.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License version 2 for more details:
 * <https://www.gnu.org/licenses/old-licenses/gpl-2.0.html>.
 *
 * For commercial licensing, please contact <nieuwmijnleven@outlook.com>.
 *
 * Contributors to this project must sign a Contributor License Agreement (CLA)
 * granting Cheol Jeon the right to relicense their contributions under
 * a commercial license. See the CLA file in the project root for details.
 */

package jplus.analyzer.nullability.issue;

import org.jspecify.annotations.NonNull;

import java.util.Objects;

public record NullabilityIssue(@NonNull IssueCode issueCode, Severity severity, int line, int column, int offset, String message) implements Comparable<NullabilityIssue> {

    public NullabilityIssue(@NonNull IssueCode issueCode, int line, int column, int offset, String message) {
        this(issueCode, issueCode.getSeverity(), line, column, offset, message);
    }

    public NullabilityIssue {
        // 필드 검증 가능
        if (line < 0 || column < 0) {
            throw new IllegalArgumentException("line and column must be >= 0");
        }

        if (offset < 0) {
            throw new IllegalArgumentException("offset must be >= 0");
        }

        Objects.requireNonNull(issueCode, "IssueCode cannot be null");
    }

    @Override
    public int compareTo(NullabilityIssue other) {
        int diff = Integer.compare(this.offset, other.offset);
        if (diff != 0) return diff;

        return Integer.compare(
                this.issueCode.ordinal(),
                other.issueCode.ordinal()
        );
    }

    @Override
    public @NonNull String toString() {
        return String.format("%s: (line:%d, column:%d) %s", severity, line, column, message);
    }
}