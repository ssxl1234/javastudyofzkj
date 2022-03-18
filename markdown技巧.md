# markdown快速入门

## 1. 基本用法
>
> - 上标
>  30^th^
> - 下标
>  H~2~O
> - 脚注
>  Content [^1]
> [^1]:Hi! this is a footnote
> - 标记
>  ==marked==

## 2. 任务列表

- [x] @mentions, #refs, [links](), **formatting**, and <del>tags</del> supported
- [x] list syntax required (any unordered or ordered list supported)
- [x] this is a complete item
- [ ] this is an incomplete item

## 3. 数学

md使用KaTeX或者MathJax来渲染数学表达式
|Katex|MathJax|
-------|------
|性能快|特性多|

- 默认以$$包裹或\(\)的数学表达式在行内显示

> $f(x)=sin(x)+12$

- 默认在*\$\$ \$\$*或者\\[ \\]中的表达式在块内显示

> $$\sum_{n=1}^{100} n$$
>