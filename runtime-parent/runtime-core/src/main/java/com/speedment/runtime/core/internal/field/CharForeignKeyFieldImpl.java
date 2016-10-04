/**
 * 
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at: 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.core.internal.field;

import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.core.field.CharField;
import com.speedment.runtime.core.field.CharForeignKeyField;
import com.speedment.runtime.core.field.method.BackwardFinder;
import com.speedment.runtime.core.field.method.CharGetter;
import com.speedment.runtime.core.field.method.CharSetter;
import com.speedment.runtime.core.field.method.FindFrom;
import com.speedment.runtime.core.field.predicate.FieldPredicate;
import com.speedment.runtime.core.field.predicate.Inclusion;
import com.speedment.runtime.core.internal.field.comparator.CharFieldComparator;
import com.speedment.runtime.core.internal.field.comparator.CharFieldComparatorImpl;
import com.speedment.runtime.core.internal.field.finder.FindFromChar;
import com.speedment.runtime.core.internal.field.predicate.chars.CharBetweenPredicate;
import com.speedment.runtime.core.internal.field.predicate.chars.CharEqualPredicate;
import com.speedment.runtime.core.internal.field.predicate.chars.CharGreaterOrEqualPredicate;
import com.speedment.runtime.core.internal.field.predicate.chars.CharGreaterThanPredicate;
import com.speedment.runtime.core.internal.field.predicate.chars.CharInPredicate;
import com.speedment.runtime.core.internal.field.streamer.BackwardFinderImpl;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.typemapper.TypeMapper;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Generated;
import static java.util.Objects.requireNonNull;

/**
 * @param <ENTITY>    entity type
 * @param <D>         database type
 * @param <FK_ENTITY> foreign entity type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
@Generated(value = "Speedment")
public final class CharForeignKeyFieldImpl<ENTITY, D, FK_ENTITY> implements CharField<ENTITY, D>, CharForeignKeyField<ENTITY, D, FK_ENTITY> {
    
    private final ColumnIdentifier<ENTITY> identifier;
    private final CharGetter<ENTITY> getter;
    private final CharSetter<ENTITY> setter;
    private final CharField<FK_ENTITY, D> referenced;
    private final TypeMapper<D, Character> typeMapper;
    private final boolean unique;
    
    public CharForeignKeyFieldImpl(ColumnIdentifier<ENTITY> identifier, CharGetter<ENTITY> getter, CharSetter<ENTITY> setter, CharField<FK_ENTITY, D> referenced, TypeMapper<D, Character> typeMapper, boolean unique) {
        this.identifier = requireNonNull(identifier);
        this.getter     = requireNonNull(getter);
        this.setter     = requireNonNull(setter);
        this.referenced = requireNonNull(referenced);
        this.typeMapper = requireNonNull(typeMapper);
        this.unique     = unique;
    }
    
    @Override
    public ColumnIdentifier<ENTITY> identifier() {
        return identifier;
    }
    
    @Override
    public CharSetter<ENTITY> setter() {
        return setter;
    }
    
    @Override
    public CharGetter<ENTITY> getter() {
        return getter;
    }
    
    @Override
    public CharField<FK_ENTITY, D> getReferencedField() {
        return referenced;
    }
    
    @Override
    public BackwardFinder<FK_ENTITY, ENTITY> backwardFinder(Manager<ENTITY> manager) {
        return new BackwardFinderImpl<>(this, manager);
    }
    
    @Override
    public FindFrom<ENTITY, FK_ENTITY> finder(Manager<FK_ENTITY> foreignManager) {
        return new FindFromChar<>(this, referenced, foreignManager);
    }
    
    @Override
    public TypeMapper<D, Character> typeMapper() {
        return typeMapper;
    }
    
    @Override
    public boolean isUnique() {
        return unique;
    }
    
    @Override
    public CharFieldComparator<ENTITY, D> comparator() {
        return new CharFieldComparatorImpl<>(this);
    }
    
    @Override
    public CharFieldComparator<ENTITY, D> comparatorNullFieldsFirst() {
        return comparator();
    }
    
    @Override
    public CharFieldComparator<ENTITY, D> comparatorNullFieldsLast() {
        return comparator();
    }
    
    @Override
    public FieldPredicate<ENTITY> equal(Character value) {
        return new CharEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterThan(Character value) {
        return new CharGreaterThanPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterOrEqual(Character value) {
        return new CharGreaterOrEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> between(Character start, Character end, Inclusion inclusion) {
        return new CharBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public FieldPredicate<ENTITY> in(Set<Character> set) {
        return new CharInPredicate<>(this, set);
    }
    
    @Override
    public Predicate<ENTITY> notEqual(Character value) {
        return new CharEqualPredicate<>(this, value).negate();
    }
    
    @Override
    public Predicate<ENTITY> lessOrEqual(Character value) {
        return new CharGreaterThanPredicate<>(this, value).negate();
    }
    
    @Override
    public Predicate<ENTITY> lessThan(Character value) {
        return new CharGreaterOrEqualPredicate<>(this, value).negate();
    }
    
    @Override
    public Predicate<ENTITY> notBetween(Character start, Character end, Inclusion inclusion) {
        return new CharBetweenPredicate<>(this, start, end, inclusion).negate();
    }
    
    @Override
    public Predicate<ENTITY> notIn(Set<Character> set) {
        return new CharInPredicate<>(this, set).negate();
    }
}