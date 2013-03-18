package com.github.jasonrose.crud.om;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.github.jasonrose.crud.om.AbstractFinder.Pred;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * This is mostly a ripoff of Guava's Predicates class, because I can't extend them to generate criteria at runtime.
 * @author Jason Rose
 * 
 */
public class Preds {

  private Preds() {
  }

  public static <E> Pred<E> isNull() {
    return new IsNullPred<E>();
  }

  public static <E> Pred<E> eq(final E value) {
    return value == null ? new IsNullPred<E>() : new IsEqualPred<E>(value);
  }

  public static <E> Pred<E> in(final E value, final E... values) {
    return in(toCollection(value, values));
  }

  public static <E> Pred<E> in(final Collection<E> values) {
    Preconditions.checkArgument(values.size() > 0, "Must provide at least one argument.");
    return new IsInPred<E>(values);
  }

  public static <E> Pred<E> not(final Pred<E> value) {
    return new IsNotPred<E>(value);
  }

  public static <E> Pred<E> and(final Pred<E> value, final Pred<E>... values) {
    return new IsAndPred<E>(toCollection(value, values));
  }

  public static <E> Pred<E> or(final Pred<E> value, final Pred<E>... values) {
    return new IsOrPred<E>(toCollection(value, values));
  }

  public static <E extends Comparable<E>> Pred<E> lt(final E value) {
    return new IsLtPred<E>(value);
  }

  public static <E extends Comparable<E>> Pred<E> lte(final E value) {
    return new IsLtePred<E>(value);
  }

  public static <E extends Comparable<E>> Pred<E> gt(final E value) {
    return new IsGtPred<E>(value);
  }

  public static <E extends Comparable<E>> Pred<E> gte(final E value) {
    return new IsGtePred<E>(value);
  }

  private static <E> Collection<E> toCollection(final E first, final E... rest) {
    final List<E> list = Lists.newArrayList(rest);
    list.add(0, first);
    return list;
  }

  private static class IsGtePred<E extends Comparable<E>> extends AbstractFinder.Pred<E> {
    private final E value;

    public IsGtePred(final E value) {
      this.value = value;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return qb.greaterThanOrEqualTo(path, value);
    }
  }

  private static class IsGtPred<E extends Comparable<E>> extends AbstractFinder.Pred<E> {
    private final E value;

    public IsGtPred(final E value) {
      this.value = value;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return qb.greaterThan(path, value);
    }
  }

  private static class IsLtePred<E extends Comparable<E>> extends AbstractFinder.Pred<E> {
    private final E value;

    public IsLtePred(final E value) {
      this.value = value;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return qb.lessThanOrEqualTo(path, value);
    }
  }

  private static class IsLtPred<E extends Comparable<E>> extends AbstractFinder.Pred<E> {
    private final E value;

    public IsLtPred(final E value) {
      this.value = value;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return qb.lessThan(path, value);
    }
  }

  private static class IsNullPred<E> extends AbstractFinder.Pred<E> {
    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return qb.isNull(path);
    }
  }

  private static class IsEqualPred<E> extends AbstractFinder.Pred<E> {
    private final E value;

    public IsEqualPred(final E value) {
      this.value = value;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return qb.equal(path, value);
    }
  }

  private static class IsInPred<E> extends AbstractFinder.Pred<E> {
    private final Collection<E> values;

    public IsInPred(final Collection<E> values) {
      this.values = values;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return path.in(values);
    }
  }

  private static class IsNotPred<E> extends AbstractFinder.Pred<E> {
    private final Pred<E> pred;

    public IsNotPred(final Pred<E> pred) {
      this.pred = pred;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      return qb.not(pred.toExpression(path, qb));
    }
  }

  private static class IsAndPred<E> extends AbstractFinder.Pred<E> {
    private final Collection<Pred<E>> values;

    public IsAndPred(final Collection<Pred<E>> values) {
      this.values = values;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      Predicate condition = qb.conjunction();
      for( final Pred<E> pred : values ) {
        condition = qb.and(condition, pred.toExpression(path, qb));
      }
      return condition;
    }
  }

  private static class IsOrPred<E> extends AbstractFinder.Pred<E> {
    private final Collection<Pred<E>> values;

    public IsOrPred(final Collection<Pred<E>> values) {
      this.values = values;
    }

    @Override
    public Expression<Boolean> toExpression(final Path<E> path, final CriteriaBuilder qb) {
      Predicate condition = qb.disjunction();
      for( final Pred<E> pred : values ) {
        condition = qb.or(condition, pred.toExpression(path, qb));
      }
      return condition;
    }
  }
}
