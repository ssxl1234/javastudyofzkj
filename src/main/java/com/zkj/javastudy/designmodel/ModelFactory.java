package com.zkj.javastudy.designmodel;
/**
 * 工厂模式
 * 意图：定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
 * 主要解决：主要解决接口选择的问题。
 * 何时使用：我们明确地计划不同条件下创建不同实例时。
 * 如何解决：让其子类实现工厂接口，返回的也是一个抽象的产品。
 * 关键代码：创建过程在其子类执行。
 * 
 * 抽象工厂模式
 * 意图：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 * 主要解决：主要解决接口选择的问题。
 * 何时使用：系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。
 * 如何解决：在一个产品族里面，定义多个产品。
 * 关键代码：在一个工厂里聚合多个同类产品。
 * @ClassName: ModelFactory
 * @Description: 
 * @author Zhukj
 * @date 2022-03-02 19:32
 *
 */
interface Animals{
	void run_1();
}
class Tiger implements Animals{

	@Override
	public void run_1() {
		System.out.println("The Tiger is run_1ning.....");
	}
	
}
class Horse implements Animals{

	@Override
	public void run_1() {
		System.out.println("The Horse is run_1ning.....");
	}
	
}
interface Color{
	void fill();
}

class Red implements Color{
	public void fill() {
		System.err.println("This is a Red Color");
	};
}

class Blue implements Color{
	public void fill() {
		System.out.println("this is a Bule Color");
	};
}
abstract class AbstractModelFactory{
	public abstract Animals createAnimals(String anmalsType);
	public abstract Color createColor(String colorType);
}
class AnimalModelFactory extends AbstractModelFactory{
	public Animals createAnimals(String anmalsType) {
		if(anmalsType.equalsIgnoreCase("tiger")) {
			return new Tiger();
		}
		if(anmalsType.equals("horse")) {
			return new Horse();
		}
		return null;
	}
	public Color createColor(String colorType) {
		if(colorType.equalsIgnoreCase("red")) {
			return new Red();
		}
		if (colorType.equalsIgnoreCase("blue")) {
			return new Blue();
		}
		return null;
	}
}
public class ModelFactory {
	public static Animals createAnimal(String wanted) {
		if(wanted.equals("tiger")) {
			return new Tiger();
		}
		if(wanted.equals("horse")) {
			return new Horse();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Animals tiger=ModelFactory.createAnimal("tiger");
		Animals horse=ModelFactory.createAnimal("horse");
		tiger.run_1();
		horse.run_1();
		new AnimalModelFactory().createColor("red").fill();;
	}
}
