package com.romix.akka.serialization.kryo

import akka.actor.TypedActor.MethodCall
import com.esotericsoftware.kryo.{Kryo, Serializer}
import com.esotericsoftware.kryo.io.{Output, Input}

/**
 *
 * @author Vladislav Isenbaev (vladislav.isenbaev@odnoklassniki.ru)
 */

class MethodCallSerializer extends Serializer[MethodCall] {
  def write(kryo: Kryo, output: Output, obj: MethodCall) {
    kryo.writeClass(output, obj.method.getDeclaringClass)
    output.writeString(obj.method.getName)
    kryo.writeObject(output, obj.method.getParameterTypes)
    kryo.writeObjectOrNull(output, obj.parameters, classOf[Array[AnyRef]])
  }

  def read(kryo: Kryo, input: Input, clazz: Class[MethodCall]) = {
    kryo.reference(null)
    val ownerType = kryo.readClass(input).getType
    val methodName = input.readString()
    val paramTypes = kryo.readObject(input, classOf[Array[Class[_]]])
    val params = kryo.readObjectOrNull(input, classOf[Array[AnyRef]])
    new MethodCall(ownerType.getDeclaredMethod(methodName, paramTypes: _*), params)
  }
}
