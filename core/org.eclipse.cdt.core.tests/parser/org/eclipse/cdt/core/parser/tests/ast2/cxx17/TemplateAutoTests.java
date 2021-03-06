/*******************************************************************************
 * Copyright (c) 2018 Vlad Ivanov
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Vlad Ivanov (LabSystems) - Initial implementation
 *******************************************************************************/
package org.eclipse.cdt.core.parser.tests.ast2.cxx17;

import org.eclipse.cdt.core.parser.tests.ast2.AST2CPPTestBase;

import junit.framework.TestSuite;

public class TemplateAutoTests extends AST2CPPTestBase {

	public static TestSuite suite() {
		return suite(TemplateAutoTests.class);
	}

	//	template<auto F>
	//	struct C {
	//		using T = decltype(F);
	//	};
	//
	//	void foo() {
	//
	//	}
	//
	//	using A = C<&foo>::T;
	public void testTemplateNontypeParameterTypeDeductionParsing_519361() throws Exception {
		parseAndCheckBindings();
	}

	//	template<typename T>
	//	struct Helper {};
	//
	//	void test() {}
	//
	//	template<auto F>
	//	struct C {
	//		using T = decltype(F);
	//		using H = Helper<T>;
	//	};
	public void testTemplateNontypeParameterTypeDeductionParsing_519361_2() throws Exception {
		parseAndCheckBindings();
	}

	//	template<typename Type, Type v>
	//	struct helper {
	//		static void call() {}
	//	};
	//
	//	template<auto F>
	//	class call_helper {
	//		using functor_t = decltype(F);
	//
	//	public:
	//		using type = helper<functor_t, F>;
	//	};
	//
	//	struct Something {
	//		void foo() {}
	//	};
	//
	//	void test() {
	//		using A = call_helper<&Something::foo>::type;
	//		A::call();
	//	}
	public void testTemplateNontypeParameterTypeDeductionParsing_519361_3() throws Exception {
		parseAndCheckBindings();
	}

	//	template <typename T, T>
	//	struct meta { using type = int; };
	//
	//	template <typename T>
	//	struct remove_noexcept { using type = T; };
	//
	//	template <typename T>
	//	using remove_noexcept_t = typename remove_noexcept<T>::type;
	//
	//	template <auto Key>
	//	struct K : meta<remove_noexcept_t<decltype(Key)>,Key>{};
	//
	//	template <auto Key>
	//	struct W {
	//	  using type = typename K<Key>::type;
	//	};
	//
	//	template <typename T>
	//	struct M {};
	//
	//	struct A {
	//	    int foo;
	//	};
	//	typedef M<W<&A::foo>::type> M1; // typedef #1
	//
	//	struct B {
	//	    int foo;
	//	};
	//	typedef M<W<&B::foo>::type> M2; // typedef #2
	public void testInstantiationCacheConflict_553141() throws Exception {
		parseAndCheckBindings();
	}
}
