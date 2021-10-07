package org.giogt.algorithms.collections;

import lombok.*;

@Data
@RequiredArgsConstructor
public class BinaryTreeNode<V> {
  @NonNull
  private V value;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private BinaryTreeNode<V> left;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private BinaryTreeNode<V> right;
}
