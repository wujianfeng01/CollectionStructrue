package DesignMode.TemplateMode;

/**
 * 模板设计模式
 * 定义模板template
 */
public  abstract  class Template {
    /**
     * 钩子方法tryDoSomething
     * 延迟到子类实现
     * @return
     */
    abstract boolean tryDoSomething();

    /**
     * 钩子方法tryNotDoSomething
     * 延迟到子类实现
     * @return
     */
    abstract boolean tryNotDoSomething();
    public final void doSomeThing(){
        tryDoSomething();
    }
    public final void NotDoSomething(){
        tryNotDoSomething();
    }
}
