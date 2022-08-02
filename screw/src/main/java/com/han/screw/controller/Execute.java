package com.han.screw.controller;

import com.han.screw.service.GenerateTemplate;
import com.han.screw.service.HtmlGenerate;
import com.han.screw.service.MarkdownGenerate;
import com.han.screw.service.WordGenerate;

/**
 * 执行生成文档
 * 需要哪种类型就创建哪个实例
 *
 * @author han
 * @date 2022/08/02
 */
public class Execute {

    public static void main(String[] args) {
        GenerateTemplate markdown = new MarkdownGenerate();
        GenerateTemplate html = new HtmlGenerate();
        GenerateTemplate word = new WordGenerate();
        //使用不同参数即可
        markdown.execute();
    }

}
