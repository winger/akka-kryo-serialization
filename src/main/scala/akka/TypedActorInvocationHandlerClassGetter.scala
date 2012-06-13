package akka

import actor.TypedActor.TypedActorInvocationHandler

/**
 * Dirty hack
 * @author Vladislav Isenbaev (vladislav.isenbaev@odnoklassniki.ru)
 */

object TypedActorInvocationHandlerClassGetter {
  def get: Class[_] = classOf[TypedActorInvocationHandler]
}
