## Demo to highlight native image generation failure with Scala 3

Execute `sbt "++2.13.8; nativeImage"` to successfully build native image for Scala 2.

For Scala 3 run `sbt "++3.1.2; nativeImage"` and the native image genaration will fail with
```
Fatal error: java.lang.TypeNotPresentException: Type io.github.arixmkii.MyTypes$WrappedFImpl.T not present
  | => fat java.base/sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:117)
	at java.base/sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:125)
	at java.base/sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)
	at java.base/sun.reflect.generics.visitor.Reifier.reifyTypeArguments(Reifier.java:68)
	at java.base/sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:138)
	at java.base/sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)
	at java.base/sun.reflect.generics.repository.MethodRepository.computeReturnType(MethodRepository.java:75)
	at java.base/sun.reflect.generics.repository.MethodRepository.getReturnType(MethodRepository.java:66)
	at java.base/java.lang.reflect.Method.getGenericReturnType(Method.java:295)
	at com.oracle.svm.reflect.hosted.ReflectionDataBuilder.registerTypesForMethod(ReflectionDataBuilder.java:483)
	at com.oracle.svm.reflect.hosted.ReflectionDataBuilder.processMethodMetadata(ReflectionDataBuilder.java:284)
	at com.oracle.svm.reflect.hosted.ReflectionDataBuilder.duringAnalysis(ReflectionDataBuilder.java:185)
	at com.oracle.svm.reflect.hosted.ReflectionFeature.duringAnalysis(ReflectionFeature.java:189)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$runPointsToAnalysis$10(NativeImageGenerator.java:726)
	at com.oracle.svm.hosted.FeatureHandler.forEachFeature(FeatureHandler.java:74)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$runPointsToAnalysis$11(NativeImageGenerator.java:726)
	at com.oracle.graal.pointsto.PointsToAnalysis.runAnalysis(PointsToAnalysis.java:751)
	at com.oracle.svm.hosted.NativeImageGenerator.runPointsToAnalysis(NativeImageGenerator.java:723)
	at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:558)
	at com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:515)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:407)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:585)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.main(NativeImageGeneratorRunner.java:128)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner$JDK9Plus.main(NativeImageGeneratorRunner.java:615)
Caused by: java.lang.ClassNotFoundException: io.github.arixmkii.MyTypes$WrappedFImpl.T
	at java.base/java.net.URLClassLoader.findClass(URLClassLoader.java:445)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:587)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:520)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Class.java:467)
	at java.base/sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:114)
```
