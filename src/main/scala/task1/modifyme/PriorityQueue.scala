package task1.modifyme

/**
  * A priority queue of priorities `K` to values `V`.
  */
trait PriorityQueue[K, V] {
  // returns `true` if nothing is enqueued in the queue
  def isEmpty: Boolean
  // removes the element of the lowest priority
  def deleteMin(): Unit
  // retrieves the priority of the lowest element
  def priority: K
  // retrieves the element of the lowest priority
  def min: V
  // adds the element `v` with priority `k`
  def enqueue(k: K, v: V): Unit

  //def debug(): Unit
}

object PriorityQueue {

  def intPriority[V]: PriorityQueue[Int, V] = {
    Queue.empty[V]
  }
}

class Queue[V] private (
    underlying: scala.collection.mutable.PriorityQueue[Queue.Entry[V]]
) extends PriorityQueue[Int, V] {

  private[this] def entry: Queue.Entry[V] =
    if (isEmpty) null else underlying.head

  @inline def isEmpty: Boolean = underlying.isEmpty

  def deleteMin(): Unit = {
    if (!underlying.isEmpty) underlying.dequeue()

    ()
  }

  @inline def priority: Int = entry.priority

  @inline def min: V = entry.value

  def enqueue(k: Int, v: V): Unit = {
    val queued = Queue.Entry(k, v)
    underlying.enqueue(queued)

    ()
  }
}

object Queue {

  def empty[V]: Queue[V] = {
    new Queue[V](new scala.collection.mutable.PriorityQueue)
  }

  case class Entry[V](priority: Int, value: V) extends Ordered[Entry[V]] {
    def compare(that: Entry[V]): Int = -this.priority.compare(that.priority)
  }
}
