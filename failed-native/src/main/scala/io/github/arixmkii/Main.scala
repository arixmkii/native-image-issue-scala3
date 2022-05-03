package io.github.arixmkii

import MyTypes.*

object Main extends MyInstances {

  def method[F[_], A](implicit F: Empty[F]): A = ???

  def main(args: Array[String]): Unit = {
    method[WrappedF[Id, *], Unit]
  }

}

object MyTypes {
  // Id effect
  type Id[A] = A

  // The most basic typeclass
  trait Empty[F[_]]

  // Empty wrapped effect type and related impl
  sealed abstract class WrappedFImpl {
    type T[F[_], A]
  }

  type WrappedF[F[_], A] = instance.T[F, A]

  val instance: WrappedFImpl = new WrappedFImpl {
    type T[F[_], A] = F[A]
  }
}

trait MyInstances {

  implicit def forWrappedF[F[_]]: Empty[WrappedF[F, *]] =
    new Empty[WrappedF[F, *]] {}
}
